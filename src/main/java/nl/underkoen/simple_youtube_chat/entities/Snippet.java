package nl.underkoen.simple_youtube_chat.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Under_Koen on 08/03/2018.
 */
public class Snippet {
    @SerializedName("type")
    public String type;
    @SerializedName("liveChatId")
    public String liveChatId;
    @SerializedName("authorChannelId")
    public String authorChannelId;
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("hasDisplayContent")
    public boolean hasDisplayContent;
    @SerializedName("displayMessage")
    public String displayMessage;

    @SerializedName("fanFundingEventDetails")
    public FanFundingEventDetails fanFundingEventDetails;
    @SerializedName("textMessageDetails")
    public TextMessageDetails textMessageDetails;
    @SerializedName("messageDeletedDetail")
    public MessageDeletedDetails messageDeletedDetail;
    @SerializedName("userBannedDetails")
    public UserBannedDetails userBannedDetails;
    @SerializedName("superChatDetails")
    public SuperChatDetails superChatDetails;
}
