package review.model;

public class Actors {
  protected Integer ActorId;
  protected String ActorName;

  public Actors(Integer actorId, String actorName) {
    ActorId = actorId;
    ActorName = actorName;
  }

  public Integer getActorId() {
    return ActorId;
  }

  public void setActorId(Integer actorId) {
    ActorId = actorId;
  }

  public String getActorName() {
    return ActorName;
  }

  public void setActorName(String actorName) {
    ActorName = actorName;
  }
}
