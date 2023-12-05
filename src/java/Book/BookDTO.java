/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book;

/**
 *
 * @author legion
 */
public class BookDTO {

    Long id;
    String name;
    String genre;
    String author;
    String cover;
    String content;
    float score;
    String summary;
    int uploader_id;

    public BookDTO() {
    }

    public BookDTO(Long id, String name, String genre, String author, String cover, String content,float score,String summary,int uploader_id) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.cover = cover;
        this.content = content;
        this.score=score;
        this.summary=summary;
        this.uploader_id=uploader_id;
    }

    public int getUploader_id() {
        return uploader_id;
    }

    public void setUploader_id(int uploader_id) {
        this.uploader_id = uploader_id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
