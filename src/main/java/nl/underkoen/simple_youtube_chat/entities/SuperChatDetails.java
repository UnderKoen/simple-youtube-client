package nl.underkoen.simple_youtube_chat.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Under_Koen on 08/03/2018.
 */
public class SuperChatDetails extends FanFundingEventDetails {
    @SerializedName("tier")
    public int tier;
}
