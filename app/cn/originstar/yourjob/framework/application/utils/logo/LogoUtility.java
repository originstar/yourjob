package cn.originstar.yourjob.framework.application.utils.logo;

import java.io.File;
import java.io.IOException;

import org.im4java.core.IM4JavaException;

/**
 * LogoUtility interface
 * 
 * <p>
 * Provides functionalities to generate organization logos and user avatars.
 * </p>
 * 
 * @author Yonggang Yuan
 */
public interface LogoUtility {

    String USER_AVATAR_FILE_NAME = "avatar.png";
    String USER_AVATAR_FILE_RAW_NAME = "avatar-raw";
    String USER_AVATAR_FILE_PENDING_NAME = "avatar-pending";
    String USER_AVATAR_FILE_TYPE = "PNG";
    Integer USER_AVATAR_WIDTH = 56;
    Integer USER_AVATAR_HEIGHT = 56;

    String ORGANIZATION_LOGO_FILE_NAME = "logo.png";
    String ORGANIZATION_LOGO_FILE_RAW_NAME = "logo-raw";
    String ORGANIZATION_LOGO_FILE_PENDING_NAME = "logo-pending";
    String ORGANIZATION_LOGO_FILE_TYPE = "PNG";
    Integer ORGANIZATION_LOGO_WIDTH = 56;
    Integer ORGANIZATION_LOGO_HEIGHT = 56;

    String PROJECT_LOGO_FILE_NAME = "logo.png";
    String PROJECT_LOGO_FILE_RAW_NAME = "logo-raw";
    String PROJECT_LOGO_FILE_PENDING_NAME = "logo-pending";
    String PROJECT_LOGO_FILE_TYPE = "PNG";
    Integer PROJECT_LOGO_WIDTH = 56;
    Integer PROJECT_LOGO_HEIGHT = 56;

    void generateOrganizationLogo(Long organizationId, File sourceFile) throws IOException, InterruptedException,
            IM4JavaException;

    void generateUserAvatar(Long userId, File sourceFile) throws IOException, InterruptedException, IM4JavaException;

    void generateProjectLogo(Long userId, File sourceFile) throws IOException, InterruptedException, IM4JavaException;

}
