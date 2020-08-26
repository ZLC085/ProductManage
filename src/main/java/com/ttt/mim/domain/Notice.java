package  com.ttt.mim.domain;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class Notice {
    @Getter @Setter private Integer id;
    @Getter @Setter private String title;
    @Getter @Setter private Integer userId;
    @Getter @Setter private String content;
    @Getter @Setter private Byte status;
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date modifyTime;
}