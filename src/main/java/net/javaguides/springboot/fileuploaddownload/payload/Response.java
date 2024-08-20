package net.javaguides.springboot.fileuploaddownload.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String id;
    private String file_name;
    private String file_download_uri;
    private String file_type;
    private long size;

}
