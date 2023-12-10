package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String textReport;
    private LocalDateTime dateTime;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] photo;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Report() {
    }

    public Report(Long chatId, String textReport, LocalDateTime dateTime, String filePath, Long fileSize, String mediaType, byte[] photo, Pet pet) {
        this.chatId = chatId;
        this.textReport = textReport;
        this.dateTime = dateTime;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.photo = photo;
        this.pet = pet;
    }

    public Long getId() {
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

    public void setTextReport(String textReport) {
        this.textReport = textReport;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(chatId, report.chatId) && Objects.equals(textReport, report.textReport) && Objects.equals(dateTime, report.dateTime) && Objects.equals(filePath, report.filePath) && Objects.equals(fileSize, report.fileSize) && Objects.equals(mediaType, report.mediaType) && Arrays.equals(photo, report.photo) && Objects.equals(pet, report.pet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, chatId, textReport, dateTime, filePath, fileSize, mediaType, pet);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
