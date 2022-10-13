package com.example.api_android.services;

public class ApisEditorial {
    public static final String URL_001="http://172.17.7.11:3000/editorial/";

    public static EditorialService getEditoriales(){
        return  Cliente.getClient(URL_001).create(EditorialService.class);
    }

    public static EditorialService getOne(){
        return  Cliente.getClient(URL_001).create(EditorialService.class);
    }
}
