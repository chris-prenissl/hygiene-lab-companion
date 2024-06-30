import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName()
        .padEnd(1)
        .plus(UIDevice.currentDevice.systemVersion)
}

actual fun getPlatform(): Platform = IOSPlatform()
