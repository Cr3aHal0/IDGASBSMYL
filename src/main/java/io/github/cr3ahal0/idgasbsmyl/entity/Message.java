package io.github.cr3ahal0.idgasbsmyl.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maxime on 04/12/2015.
 */
@JsonWriteNullProperties(false)
@JsonIgnoreProperties({"id", "revision"})
public class Message {

    public Message(MapMessage map) {
        //Try to build metadata from MapMessage
        metadatas = new HashMap<String, Object>();
        try {
            Enumeration<String> enumeration = map.getMapNames();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                metadatas.put(key, map.getObject(key));
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    @JsonProperty("metadata")
    private Map<String, Object> metadatas;

    public void setId(String s) {
        id = s;
    }

    public String getId() {
        return id;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getRevision() {
        return revision;
    }

    /**
     * Set the metadata for the current message
     * @param metadatas the metadata to set
     */
    public void setMetadatas(Map<String, Object> metadatas) {
        this.metadatas = metadatas;
    }

    /**
     * Returns the metadata of the given message
     * @return the metadata of the given message
     */
    public Map<String, Object> getMetadatas() {
        return metadatas;
    }
}
