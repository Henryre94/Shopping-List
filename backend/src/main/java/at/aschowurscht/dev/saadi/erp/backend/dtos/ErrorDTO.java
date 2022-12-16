package at.aschowurscht.dev.saadi.erp.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private int status;
    private int code;
    private String title;
    private String detail;
}
