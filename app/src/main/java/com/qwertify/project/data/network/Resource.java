package com.qwertify.project.data.network;

public interface Resource {

    class Success implements Resource {
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
