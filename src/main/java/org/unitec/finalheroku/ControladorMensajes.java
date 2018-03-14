/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.finalheroku;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")


public class ControladorMensajes {
    @Autowired RepositorioMensajito repoMensa;
    //buscar todos
    @CrossOrigin
    
    @RequestMapping(value="/mensajitos",method=RequestMethod.GET,
            headers={"Accept=application/json"})
    
    public ArrayList <Mensajitos> hola(){
        return (ArrayList <Mensajitos>) repoMensa.findAll();
    }
    
    //buscar id
    @CrossOrigin
    
    @RequestMapping(value="/mensajitos/{id}",method=RequestMethod.GET,
            headers={"Accept=application/json"})
    
    public Mensajitos hola(@PathVariable String id){
        return repoMensa.findOne(id);
    }
    
    //actualizar
    @CrossOrigin
    @RequestMapping(value="/mensajitos/{id}/{titulo}/{cuerpo}", method=RequestMethod.PUT,
            headers ={"Accept=application/json"})
    public Estatus actualizar(@PathVariable String id,@PathVariable String titulo, @PathVariable String cuerpo){
    repoMensa.save(new Mensajitos(id,titulo,cuerpo));
    return new Estatus(true,"actualizado con Exito");
    }
    //guardar
    @CrossOrigin
    @RequestMapping(value="/mensajitos/{titulo}/{cuerpo}", method=RequestMethod.POST,
            headers ={"Accept=application/json"})
    public Estatus guardar(@PathVariable String titulo, @PathVariable String cuerpo){
    repoMensa.save(new Mensajitos(titulo,cuerpo));
    return new Estatus(true,"guardado con Exito");
    }
    
    //guardar dos
    
    @CrossOrigin
    @RequestMapping(value="/mensajito",method=RequestMethod.POST, headers ={"Accept=application/json"})
    public Estatus guardarJSON(@RequestBody String json)throws Exception{
      ObjectMapper maper=new ObjectMapper();
      Mensajitos mensa= maper.readValue(json, Mensajitos.class);
      repoMensa.save(mensa);
      return new Estatus(true, "Guardado con exito");
    
    
    }
    
    
    //borrar
    @CrossOrigin
@RequestMapping(value="/mensajitos/{id}", method=RequestMethod.DELETE,
            headers ={"Accept=application/json"})
    public Estatus borrarMensaje(@PathVariable String id){
        Estatus estatus=new Estatus(true, "Borrado con exito"); 
    repoMensa.delete(new Mensajitos(id));
    return estatus;
    }
}