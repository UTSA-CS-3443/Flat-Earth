package server.skills;

import communicators.serverToClient.SkillState;
import server.entities.PosAndDir;
import server.entities.ServerCharacter;

public abstract class ServerSkill
{
	public ServerCharacter performer;
	
    public abstract void perform(PosAndDir initial);

    public abstract float getPower();
    
    public abstract ServerSkill copy();
    
    public abstract SkillState getState();

    public abstract void setPerformer(ServerCharacter c);
    
}
