package review.model;

public class Collaboration {
  protected Integer CollaborationId;
  protected Movies movie;
  protected Actors actor;
  protected Directors director;

  public Collaboration(Integer collaborationId, Movies movie, Actors actor, Directors director) {
    CollaborationId = collaborationId;
    this.movie = movie;
    this.actor = actor;
    this.director = director;
  }

  public Collaboration(Integer collaborationId) {
    CollaborationId = collaborationId;
  }

  public Collaboration(Movies movie, Actors actor, Directors director) {
    this.movie = movie;
    this.actor = actor;
    this.director = director;
  }

  public Integer getCollaborationId() {
    return CollaborationId;
  }

  public void setCollaborationId(Integer collaborationId) {
    CollaborationId = collaborationId;
  }

  public Movies getMovie() {
    return movie;
  }

  public void setMovie(Movies movie) {
    this.movie = movie;
  }

  public Actors getActor() {
    return actor;
  }

  public void setActor(Actors actor) {
    this.actor = actor;
  }

  public Directors getDirector() {
    return director;
  }

  public void setDirector(Directors director) {
    this.director = director;
  }
}
