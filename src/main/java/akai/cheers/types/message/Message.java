package akai.cheers.types.message;

public class Message {
    private Metadata metadata;
    private Payload payload;

    public Metadata getMetadata() { return metadata; }
    public void setMetadata(Metadata value) { this.metadata = value; }

    public Payload getPayload() { return payload; }
    public void setPayload(Payload value) { this.payload = value; }
}
