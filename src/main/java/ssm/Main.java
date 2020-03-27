package ssm;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ssm.proxy.CommonProxy;

@Mod(modid = Main.MOD_ID, name = Main.NAME, version = Main.VERSION)
public class Main {
    @Mod.Instance
    public static Main instance;
    public static final String MOD_ID = "ssm";
    public static final String NAME = "SuperShapeMod";
    public static final String VERSION = "1.0_BETA";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String CLIENT_PROXY_CLASS = "ssm.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "ssm.proxy.CommonProxy";

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event){

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event){

    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event){

    }
}
