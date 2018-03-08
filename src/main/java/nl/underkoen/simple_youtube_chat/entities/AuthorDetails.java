package nl.underkoen.simple_youtube_chat.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Under_Koen on 08/03/2018.
 */
public class AuthorDetails {
    @SerializedName("channelId")
    public String channelId;
    @SerializedName("channelUrl")
    public String channelUrl;
    @SerializedName("displayName")
    public String displayName;
    @SerializedName("profileImageUrl")
    public String profileImageUrl;
    @SerializedName("isVerified")
    public boolean isVerified;
    @SerializedName("isChatOwner")
    public boolean isChatOwner;
    @SerializedName("isChatSponsor")
    public boolean isChatSponsor;
    @SerializedName("isChatModerator")
    public boolean isChatModerator;
}
