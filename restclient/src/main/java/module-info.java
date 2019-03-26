module restclient {
    requires gson;
    requires org.apache.httpcomponents.httpclient;
    requires slf4j.api;
    requires org.apache.httpcomponents.httpcore;
    requires java.sql;

    exports nl.fhict.s3.restclient.model; // needed vor gson
    opens nl.fhict.s3.restclient.model;   // needed for gson
}