package example.hellomod.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class HelloMixin {
    @Inject(at = @At(value="TAIL"), method = "onPlayerConnect")
    private void modifyPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci){
        System.out.println("Hello " + player.getName().asString() + "!");
    }
}
