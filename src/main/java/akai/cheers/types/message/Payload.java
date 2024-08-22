package akai.cheers.types.message;

public class Payload {
    private Session session;
    private Subscription subscription;
    private Event event;

    public Session getSession() { return session; }
    public void setSession(Session value) { this.session = value; }

    public Subscription getSubscription() { return subscription; }
    public void setSubscription(Subscription value) { this.subscription = value; }

    public Event getEvent() { return event; }
    public void setEvent(Event value) { this.event = value; }
}
