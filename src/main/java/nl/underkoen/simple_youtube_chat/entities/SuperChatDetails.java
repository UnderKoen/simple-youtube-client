package nl.underkoen.simple_youtube_chat.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Under_Koen on 08/03/2018.
 */
public class SuperChatDetails {
    @SerializedName("amountMicros")
    public long amountMicros;
    @SerializedName("currency")
    public String currency;
    @SerializedName("amountDisplayString")
    public String amountDisplayString;
    @SerializedName("userComment")
    public String userComment;
    @SerializedName("tier")
    public int tier;
}
