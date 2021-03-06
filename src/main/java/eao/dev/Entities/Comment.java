package eao.dev.Entities;

import eao.dev.Entities.Abstract.BaseEntity;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Post post;

    @Column(name = "post_id")
    private long postId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "name")
    private String name;

    @Column(name = "timestamp", insertable = false, updatable = false)
    private Timestamp timestamp;

    public long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public JSONObject toJson() {
        JSONObject commentObject = new JSONObject();
        commentObject.put("name", name);
        commentObject.put("comment", comment);
        commentObject.put("timestamp", timestamp.toString());
        return commentObject;
    }
}
