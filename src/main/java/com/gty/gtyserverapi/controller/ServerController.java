package com.gty.gtyserverapi.controller;



import com.gty.gtyserverapi.model.category;
import com.gty.gtyserverapi.model.chanel;
import com.gty.gtyserverapi.model.server;
import com.gty.gtyserverapi.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/server")
@CrossOrigin(origins = "*")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public server loadServer(@RequestBody server _server){
       return serverService.loadServer(_server);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public server getServer(){
        return serverService.getServer();
    }
    @GetMapping("category")
    @ResponseStatus(HttpStatus.OK)
    public List<category> getAllCategory(){
        return serverService.getAllCategory();
    }
    @GetMapping("{categoryid}")
    @ResponseStatus(HttpStatus.OK)
    public List<chanel> getChanel(@PathVariable("categoryid") String categoryid){
        return serverService.getChanelByCategoryID(categoryid);
    }
    @GetMapping("chanels")
    @ResponseStatus(HttpStatus.OK)
    public List<chanel> getAllChanel(){
        return serverService.getAllChanel();
    }
    @GetMapping ("infoserver")
    @ResponseStatus(HttpStatus.OK)
    public String infoServer(){
        return serverService.getInfoServer();
    }

    @GetMapping("checkserver")
    @ResponseStatus(HttpStatus.OK)
    public void checkServer(){
        serverService.checkServer();
    }
}
