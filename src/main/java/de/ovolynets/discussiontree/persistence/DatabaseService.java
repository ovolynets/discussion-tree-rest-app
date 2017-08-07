package de.ovolynets.discussiontree.persistence;

import java.util.Optional;

import de.ovolynets.discussiontree.domain.MessageEntry;

public interface DatabaseService {

    int addNew(String text);

    void addReply(int postId, String text);

    Optional<MessageEntry> get(int postId);

    void delete(int postId);
}
