package br.com.dougluciano.boadtaskjava.enums;

import lombok.Getter;


public enum ApplicationRoutes {
      FORM_LOGIN ("/login"),
      CSS_DIRECTORY ("/css/**"),
      JAVA_SCRIPT_DIRECTORY ("/js/**"),
      ASSETS_DIRECTORY ("/assets/**"),
      TASKS_URL ("/tasks"),
      USERS_URL ("/users"),
      NEW_USER("/new"),
      REGISTER_URL(USERS_URL.getPath() + NEW_USER.getPath());

      private final String path;

      ApplicationRoutes(String path){
          this.path = path;
      }

      public String getPath(){
          return path;
      }


}
