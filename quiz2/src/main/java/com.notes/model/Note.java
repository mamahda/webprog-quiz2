package com.notes.model;

import java.sql.Timestamp;

public class Note {
    private int id;
    private String title;
    private String content;
    private boolean isPinned;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 1. Constructor Kosong (Wajib ada untuk POJO)
    public Note() {}

    // 2. Constructor Lengkap (Untuk Mapping dari Database)
    public Note(int id, String title, String content, boolean isPinned, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPinned = isPinned;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 3. Constructor INSERT (Untuk Insert Note Baru)
    // Ini yang dicari oleh error: Note(String, String, boolean)
    public Note(String title, String content, boolean isPinned) {
        this.title = title;
        this.content = content;
        this.isPinned = isPinned;
    }

    // 4. Constructor UPDATE (Untuk Edit Content)
    // Kita buat khusus biar Servlet tidak perlu kirim null atau boolean palsu
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public boolean isPinned() { return isPinned; }
    public void setPinned(boolean pinned) { isPinned = pinned; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}