package tsarionovtimofey.tests.wiring

import dagger.Module
import tsarionovtimofey.tests.wiring.login.LoginWiringDiModule

@Module(includes = [LoginWiringDiModule::class])
interface WiringDiModule