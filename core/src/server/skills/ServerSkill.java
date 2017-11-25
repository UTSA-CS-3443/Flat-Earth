package server.skills;

import communicators.serverToClient.SkillState;
import server.entities.PosAndDir;

public interface ServerSkill
{
    void perform(PosAndDir initial);

    float getPower();
    
    ServerSkill copy();
    
    SkillState getState();
    
}
