package com.example.demo.board.repository;

public class BoardDTO {
    private Integer id;
    private String writer;
    private String title;
    private String content;
    private Integer likeCount;



    public BoardDTO() {}

    public BoardDTO(Integer id, String writer, String title, String content, Integer likeCount) {
        super();
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }





}
