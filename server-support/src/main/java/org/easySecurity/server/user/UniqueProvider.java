package org.easySecurity.server.user;

import org.easySecurity.core.user.UserInfo;

public interface UniqueProvider {
    UserInfo extractUseDetails(UniqueAccessor accessor);
}
