package akai.cheers.types.message;

public class Subscription {
    private String id;
    private String status;
    private String type;
    private String version;
    private Condition condition;
    private Transport transport;
    private String created_at;
    private long cost;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getVersion() { return version; }
    public void setVersion(String value) { this.version = value; }

    public Condition getCondition() { return condition; }
    public void setCondition(Condition value) { this.condition = value; }

    public Transport getTransport() { return transport; }
    public void setTransport(Transport value) { this.transport = value; }

    public String getCreatedAt() { return created_at; }
    public void setCreatedAt(String value) { this.created_at = value; }

    public long getCost() { return cost; }
    public void setCost(long value) { this.cost = value; }
}
