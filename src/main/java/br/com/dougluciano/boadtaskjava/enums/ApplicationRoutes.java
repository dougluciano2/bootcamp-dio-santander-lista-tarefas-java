package br.com.dougluciano.boadtaskjava.enums;

import lombok.Getter;

@Getter
public enum ApplicationRoutes {
      FORM_LOGIN ("/login"),
      CSS_DIRECTORY ("/css/**"),
      JAVA_SCRIPT_DIRECTORY ("/js/**"),
      ASSETS_DIRECTORY ("/assets/**"),
      TASKS_URL ("/tasks"),
      USERS_URL ("/users");

      private final String path;

      ApplicationRoutes(String message){
          this.path = message;
      }


}
