package com.ai.common.controller;

/** Raised when an anonymous anonymous id cookie is missing from the request context. */
public class AnonymousIdRequiredException extends RuntimeException {
  public AnonymousIdRequiredException() {
    super("Anonymous id is required");
  }
}
