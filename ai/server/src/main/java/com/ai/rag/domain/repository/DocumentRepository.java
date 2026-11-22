package com.ai.rag.domain.repository;

import com.ai.common.domain.model.OwnerId;
import com.ai.common.domain.repository.OwnerPartitionedRepository;
import com.ai.rag.domain.model.DocumentId;
import com.ai.rag.domain.model.RagDocument;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/** Repository of uploaded documents, partitioned by owner. */
public interface DocumentRepository extends OwnerPartitionedRepository<RagDocument, DocumentId> {

  /** Finds the owner's document by id. */
  Optional<RagDocument> findByIdAndOwnerId(DocumentId id, OwnerId ownerId);

  /** Lists the owner's documents, newest first. */
  List<RagDocument> findAllByOwnerIdOrderByCreatedAtDesc(OwnerId ownerId);

  /** Saves the document and returns the stored copy. */
  RagDocument save(RagDocument document);

  /** Deletes the owner's document by id. */
  @Transactional
  void deleteByIdAndOwnerId(DocumentId id, OwnerId ownerId);
}
