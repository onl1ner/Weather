module edu.tsatualdypov.app {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    requires transitive okhttp3;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive okio;
    requires transitive kotlin.stdlib;

    exports edu.tsatualdypov.app;
    exports edu.tsatualdypov.app.models;
    exports edu.tsatualdypov.app.services.network;
    exports edu.tsatualdypov.app.controllers;
    exports edu.tsatualdypov.app.ui;

    opens edu.tsatualdypov.app.ui;
    opens edu.tsatualdypov.app.models;
    opens edu.tsatualdypov.app.controllers;
}