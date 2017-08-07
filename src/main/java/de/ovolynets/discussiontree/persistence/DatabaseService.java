package de.ovolynets.discussiontree.persistence;

import java.util.Optional;

import de.ovolynets.discussiontree.domain.MessageEntry;

public interface DatabaseService {

    int addNew(String text);

    Optional<Integer> addReply(int postId, String text);

    Optional<MessageEntry> get(int postId);

    Optional<MessageEntry> delete(int postId);
}
