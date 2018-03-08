package nl.underkoen.simple_youtube_chat.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Under_Koen on 08/03/2018.
 */
public class Message {
    @SerializedName("etag")
    public String etag;
    @SerializedName("id")
    public String id;

    @SerializedName("snippet")
    public Snippet snippet;

    @SerializedName("authorDetails")
    public AuthorDetails authorDetails;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Message) {
            Message message = (Message) obj;
            if (message.id.equals(id)) return true;
        }
        return super.equals(obj);
    }
}