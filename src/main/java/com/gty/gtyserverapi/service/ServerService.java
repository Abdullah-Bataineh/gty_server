package com.gty.gtyserverapi.service;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gty.gtyserverapi.model.*;
import com.gty.gtyserverapi.repository.CategoryRepository;
import com.gty.gtyserverapi.repository.ChanelRepository;
import com.gty.gtyserverapi.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServerService {
    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ChanelRepository chanelRepository;

    @Autowired
    private server _server;
    @Autowired
    private chanel _chanel;
    @Autowired
    private category _category;
    @Autowired
    private EmailServerService emailServerService;

    public server loadServer(server _server){
       serverRepository.deleteAll();

        RestTemplate restTemplate=new RestTemplate();
        //Get Categories
        String uriGetCategories=_server.getHost()+"/player_api.php?username="+_server.getUsername()+"&password="+_server.getPassword()+"&action=get_live_categories";
        ParameterizedTypeReference<List<_Category>> responseCategoryType = new ParameterizedTypeReference<List<_Category>>() {};
        ResponseEntity<List<_Category>> responseCategoryEntity = restTemplate.exchange(uriGetCategories, HttpMethod.GET, null, responseCategoryType);
        List<_Category> resultCategories = responseCategoryEntity.getBody();
        saveCategories(resultCategories);
      //Get Chanels
        String uriGetChanels=_server.getHost()+"/player_api.php?username="+_server.getUsername()+"&password="+_server.getPassword()+"&action=get_live_streams";
      ParameterizedTypeReference<List<_Chanels>> responseChanelType = new ParameterizedTypeReference<List<_Chanels>>() {};
        ResponseEntity<List<_Chanels>> responseChanelEntity = restTemplate.exchange(uriGetChanels, HttpMethod.GET, null, responseChanelType);
        List<_Chanels> resultChanels = responseChanelEntity.getBody();
        saveChanels(resultChanels);
        return serverRepository.save(_server);

    }
    public void checkServer(){
        List<server> existingServer=serverRepository.findAll();
        server getServer=existingServer.get(0);
        String uriGetServerDetalis=getServer.getHost()+"/player_api.php?username="+getServer.getUsername()+"&password="+getServer.getPassword();
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriGetServerDetalis, String.class);
        String responseBody = responseEntity.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode statusNode = jsonNode.path("user_info").path("status");
            String statusValue = statusNode.asText();
            if(statusValue.equals("Active")){
                emailServerService.sendStatusServerByEmail(true);
            }
            else {
                emailServerService.sendStatusServerByEmail(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void saveCategories(List<_Category> category){
categoryRepository.deleteAll();
        for (_Category item : category) {
            category _category=new category();
          _category.setCategoryId(item.getCategory_id());
          _category.setCategoryName(item.getCategory_name());
          categoryRepository.save(_category);

        }
        System.out.println(true);
    }

    public void saveChanels(List<_Chanels> chanels){
chanelRepository.deleteAll();
    for(_Chanels item:chanels){
        chanel _chanel=new chanel();
        _chanel.setNameChanel(item.getName());
        _chanel.setCategoryId(item.getCategory_id());
        _chanel.setStreamId(item.getStream_id());
        _chanel.setIconUrl(item.getStream_icon());
        chanelRepository.save(_chanel);
    }

    }
    public List<category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public List<chanel> getChanelByCategoryID(String categoryId){
        List<chanel> allChanel=chanelRepository.findAll();
        List<chanel> getChanel=new ArrayList<chanel>();
        for(chanel item:allChanel){
            if(item.getCategoryId().equals(categoryId))
                getChanel.add(item);
        }
        return getChanel;
    }

    public List<chanel> getAllChanel(){
        return chanelRepository.findAll();
    }
    public String getInfoServer(){
       List<server> infoserver= serverRepository.findAll();
       server getserver=infoserver.get(0);
      return getserver.getHost()+"/player_api.php?username="+getserver.getUsername()+"&password="+getserver.getPassword();
    }
    public server getServer(){
        List<server> _server=serverRepository.findAll();
        server getserver=_server.get(0);
        return getserver;
    }

}
