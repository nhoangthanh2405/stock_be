package net.javaguides.springboot.fileuploaddownload.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
@Data
public class DatabaseFile {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String file_name;

	private String file_type;

	@Lob
	private byte[] data;

	private Boolean is_active;

	public DatabaseFile() {

	}

	public DatabaseFile(String file_name, String file_type, byte[] data) {
		this.file_name = file_name;
		this.file_type = file_type;
		this.data = data;
	}
}
