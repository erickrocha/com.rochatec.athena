package com.athena.assembler;

import com.athena.to.ProfileTO;
import com.rochatec.athena.model.Profile;

/**
 * Created by epr on 29/05/15.
 */
public class ProfileAssembler implements IAssembler<Profile,ProfileTO> {

    @Override
    public Profile toModel(ProfileTO value) {
        Profile profile = new Profile();
        profile.setId(value.getId());
        profile.setName(value.getName());
        profile.setLabel(value.getLabel());
        return profile;
    }

    @Override
    public ProfileTO to(Profile model) {
        ProfileTO profileTO = new ProfileTO();
        profileTO.setId(model.getId());
        profileTO.setLabel(model.getLabel());
        profileTO.setName(model.getName());
        return profileTO;
    }
}
