package com.qwertify.project.data.network;

public interface Resource {

    class Success implements Resource {
    }

    class Error implements Resource {
    }

    class Loading implements Resource {
    }
}
