package server.skills;

import communicators.serverToClient.SkillState;
import server.entities.PosAndDir;
import server.entities.ServerCharacter;

/**
 * Skills super class
 * 
 * @author ash
 *
 */
public abstract class ServerSkill
{
	/**
	 * who performed it
	 */
	public ServerCharacter performer;
	
    /**
     * @param initial, starts it
     */
    public abstract void perform(PosAndDir initial);

    /**
     * @return
     */
    public abstract float getPower();
    
    /**
     * @return copy of skill
     */
    public abstract ServerSkill copy();
    
    /**
     * @return state
     */
    public abstract SkillState getState();

    /**
     * @param c character returned
     */
    public abstract void setPerformer(ServerCharacter c);
    
}
