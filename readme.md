# Lamp Wrapper
A wrapper over the lamp command framework which aims to provide a suite of
additional tools and annotations to help you develop commands as quickly and
as easily as possible.


Set it up in your onEnable using `LampManager.setup(JavaPlugin, BukkitCommandHandler)`
where BukkitCommandHandler is Lamps handler which you can create using 
`BukkitCommandHandler.create(JavaPlugin)`