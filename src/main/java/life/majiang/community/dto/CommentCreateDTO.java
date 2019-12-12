package life.majiang.community.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentCreateDTO {
    @ApiModelProperty("parentId")
    private Long parentId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("类型")
    private Integer type;
}
