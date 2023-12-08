package clarkson.ee408.tictactoev4.model;

/**
 * Model class for a game event between two players in a TicTacToe game
 *
 * @author Ahmad Suleiman
 */
public class Event {

    /**
     * Various status of event, from pending all the way to completed or aborted
     */
    public enum EventStatus {
        /**
         * Status after {@link #sender} sends invitation to {@link #opponent}
         */
        PENDING,

        /**
         * Status after {@link #opponent} declines {@link #sender}'s invitation
         */
        DECLINED,

        /**
         * Status after {@link #opponent} accepts {@link #sender}'s invitation
         */
        ACCEPTED,

        /**
         * Status after {@link #sender} acknowledge {@link #opponent}'s response
         */
        PLAYING,

        /**
         * Status after game is completed. Either {@link #opponent} or {@link #sender} won, or tie
         */
        COMPLETED,

        /**
         * Status after game is aborted. Either {@link #opponent} or {@link #sender} abort game or disconnects the server
         */
        ABORTED
    }

    /**
     * Unique identifier autogenerated by a central database
     */
    int eventId;

    /**
     * The username of the player that sends the invitation
     */
    String sender;

    /**
     * The username of the player that receives the invitation
     */
    String opponent;

    /**
     * The current status of the event
     */
    EventStatus status;

    /**
     * The username of the player that made the last move
     */
    String turn;

    /**
     * The last move of the game
     */
    int move;

    /**
     * Default constructor
     */
    public Event() {
    }

    /**
     *
     * @param eventId Unique identifier autogenerated by a central database
     * @param sender The username of the player that sends the invitation
     * @param opponent The username of the player that receives the invitation
     * @param status The current status of the event
     * @param turn The username of the player that made the last move
     * @param move The last move of the game
     */
    public Event(int eventId, String sender, String opponent, EventStatus status, String turn, int move) {
        this.eventId = eventId;
        this.sender = sender;
        this.opponent = opponent;
        this.status = status;
        this.turn = turn;
        this.move = move;
    }

    /**
     * Getter functions for {@link #eventId} attribute
     * @return eventId
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * Setter function for {@link #eventId} attribute
     * @param eventId Unique identifier autogenerated by a central database
     */
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    /**
     * Getter functions for {@link #sender} attribute
     * @return sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Setter function for {@link #sender} attribute
     * @param sender The username of the player that sends the invitation
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Getter functions for {@link #opponent} attribute
     * @return opponent
     */
    public String getOpponent() {
        return opponent;
    }

    /**
     * Setter function for {@link #opponent} attribute
     * @param opponent The username of the player that receives the invitation
     */
    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    /**
     * Getter functions for {@link #status} attribute
     * @return status
     */
    public EventStatus getStatus() {
        return status;
    }

    /**
     * Setter function for {@link #status} attribute
     * @param status The current status of the event
     */
    public void setStatus(EventStatus status) {
        this.status = status;
    }

    /**
     * Getter functions for {@link #turn} attribute
     * @return turn
     */
    public String getTurn() {
        return turn;
    }

    /**
     * Setter function for {@link #turn} attribute
     * @param turn The username of the player that made the last move
     */
    public void setTurn(String turn) {
        this.turn = turn;
    }

    /**
     * Getter functions for {@link #move} attribute
     * @return lastMove
     */
    public int getMove() {
        return move;
    }

    /**
     * Setter function for {@link #move} attribute
     * @param move The last move of the game
     */
    public void setMove(int move) {
        this.move = move;
    }

    /**
     *
     * @param obj instance of the other Event object
     * @return true if both objects have equal {@link #eventId}
     */
    @Override
    public boolean equals(Object obj) {
        try {
            Event other = (Event) obj;
            return this.eventId == other.getEventId();
        } catch (ClassCastException e) {
            return false;
        }
    }
}
