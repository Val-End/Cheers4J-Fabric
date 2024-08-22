package akai.cheers.types;

import akai.cheers.types.message.Condition;
import akai.cheers.types.message.Transport;

public class SubscriptionData {
    private String type;
    private String version;
    private Condition condition;
    private Transport transport;

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getVersion() { return version; }
    public void setVersion(String value) { this.version = value; }

    public Condition getCondition() { return condition; }
    public void setCondition(Condition value) { this.condition = value; }

    public Transport getTransport() { return transport; }
    public void setTransport(Transport value) { this.transport = value; }
}
