package  com.ttt.mim.domain;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class Company {
    @Getter @Setter private Integer id;
    @Getter @Setter private String name;
    @Getter @Setter private String phone;
    @Getter @Setter private String socialCode;
    @Getter @Setter private String email;
    @Getter @Setter private Byte status;
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date modifyTime;
}