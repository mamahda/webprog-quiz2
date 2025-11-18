package com.notes.dao;

import com.notes.model.Note;
import com.notes.util.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    // 1. CREATE
    public void insertNote(Note note) {
        // Default is_pinned = 0, jadi query insert cukup title & content
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO notes (title, content, is_pinned) VALUES (?, ?, ?)")) {
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setBoolean(3, false); // Default false
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 2. READ ALL (Order by Pinned dulu, baru Updated)
    public List<Note> selectAllNotes() {
        List<Note> notes = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             // PINNED note muncul paling atas
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM notes ORDER BY is_pinned DESC, updated_at DESC")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                notes.add(mapResultSetToNote(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return notes;
    }

    // 3. READ ONE
    public Note selectNote(int id) {
        Note note = null;
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM notes WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) note = mapResultSetToNote(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return note;
    }

    // 4. SEARCH
    public List<Note> searchNotes(String keyword) {
        List<Note> notes = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM notes WHERE title LIKE ? OR content LIKE ? ORDER BY is_pinned DESC")) {
            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) notes.add(mapResultSetToNote(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return notes;
    }

    // 5. UPDATE CONTENT
    public void updateNote(Note note) {
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE notes SET title = ?, content = ? WHERE id = ?")) {
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setInt(3, note.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 6. UPDATE PIN (Fitur CRUD ke-6: Toggle Status)
    public void togglePin(int id) {
        try (Connection conn = DB.getConnection();
             // Query pintar: membalik nilai boolean (True jadi False, False jadi True)
             PreparedStatement ps = conn.prepareStatement("UPDATE notes SET is_pinned = NOT is_pinned WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 7. DELETE ONE
    public void deleteNote(int id) {
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM notes WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 8. DELETE ALL
    public void deleteAllNotes() {
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE notes")) {
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Helper Mapping (UPDATE INI PENTING)
    private Note mapResultSetToNote(ResultSet rs) throws SQLException {
        return new Note(
            rs.getInt("id"), 
            rs.getString("title"), 
            rs.getString("content"), 
            rs.getBoolean("is_pinned"), 
            rs.getTimestamp("created_at"),
            rs.getTimestamp("updated_at")
        );
    }
}