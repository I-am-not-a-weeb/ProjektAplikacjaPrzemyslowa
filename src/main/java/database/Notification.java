package Database;


import api.Serializers.NotificationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

@Entity
@JsonSerialize(using = NotificationSerializer.class)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id")
    private Account recipient;
    @Column(length = 1000)
    private String message;
    @Column(nullable = false)
    private boolean isRead;
    @Column
    private String relatedUrl;

    public Long getId() {
        return id;
    }
    public Account getRecipient() {
        return recipient;
    }
    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isIsRead() {
        return isRead;
    }
    public void setIsRead(boolean read) {
        this.isRead = read;
    }
    public String getRelatedUrl() {
        return relatedUrl;
    }
    public void setRelatedUrl(String relatedUrl) {
        this.relatedUrl = relatedUrl;
    }
}
