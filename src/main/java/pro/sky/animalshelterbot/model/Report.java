package pro.sky.animalshelterbot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс с данными об отчете о животном, который отправляют владельцы животного
 */
@Entity
public class Report {
    /**
     * идентификатор записи, primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * идентификатор чата хозяина животного, отправившего отчет
     */
    private Long chatId;
    /**
     * текст отчета
     */
    private String textReport;
    /**
     * дата отправки отчета
     */
    private LocalDateTime dateReport;

    /**
     * путь к фото
     */
    private String filePath;
    /**
     * размер фото
     */
    private long fileSize;
    /**
     * тип данных у фото
     */
    private String mediaType;
    /**
     * сам файл фото - массив байтов
     */
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    /**
     * id питомца
     */
    private Pet pet;

    public long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getTextReport() {
        return textReport;
    }

    public void setTextReport(String petReport) {
        this.textReport = petReport;
    }

    public LocalDateTime getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDateTime date) {
        this.dateReport = date;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return getId() == report.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
