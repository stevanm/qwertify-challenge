package com.qwertify.project.data.network;

import com.qwertify.project.domain.model.login.UserLogin;

public interface Resource {

    class Success implements Resource {
        public UserLogin userLogin;

        public Success(UserLogin userLogin) {
            this.userLogin = userLogin;
        }
    }

    class Error implements Resource {

        public String errorMessage;

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    class Loading implements Resource {
    }
}
