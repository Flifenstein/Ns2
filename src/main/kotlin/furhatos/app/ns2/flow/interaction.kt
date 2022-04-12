package furhatos.app.ns2.flow



import furhatos.app.ns2.gestures.rollHead
import furhatos.app.ns2.nlu.*
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.autobehavior.userSpeechStartGesture
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures



val Start : State = state(Interaction) {


    onEntry {
        furhat.setDefaultMicroexpression(blinking = true, facialMovements= true, eyeMovements = false)
        furhat.listen()
        furhat.ask("I have the personal preference to not wear a mask.")

    }

    onResponse<Neutral> {
        furhat.gesture(rollHead(-20.0, 2.3))
        furhat.say {
            random {
                +"I don't believe in the whole virus story"
                + "I had the virus a month ago and there is no chance that I get sick again that soon"
                +"You know that wearing the mask can cause more damage instead of protection, right?"
                +"I am not in the risk group, so if there is a chance I get sick, it won't be that bad.L"
                + "You know that wearing the mask can cause more damage instead of protection, right?"
                + "If only one of us is wearing the mask, it will be fine."
                +"I don't wear it in the office where all of my colleagues are, why should I wear it here where I don't talk with anyone except of the cashier who has a shield in front?"
                + "There are other people that are not wearing a mask as well."
                + "I test myself with a rapid test everyday because of my work and if it was positive, I would wear the mask."
                + "What about all those people wearing it under the nose?"

            }
            furhat.listen()

        }
    }

    onResponse<Positive>{
        furhat.say {
            furhat.gesture(Gestures.Smile(duration = 10.0), async = true)
            random {
                +"Can't you just let it go this time?"
                +"I don't think others will really mind if I don't wear a mask."
                +"So, what if I promise to stay away from the elderly?"
                +"It will be only 5 minutes. I just need some cereal. "
                + "My children are home alone waiting for me, this is taking a bit long now."
                + "My children are home alone waiting for me, this is taking a bit long now."

            }
        }
        furhat.listen()
    }


    onResponse(listOf(Negative(), ExtremeNegative())){
        furhat.say {
            furhat.gesture(Gestures.ExpressAnger(duration = 10.0), async = true)
            random {
                +"It's none of your business if I am wearing a mask or not."
                +"Oh, I don't care about that! "
                +"This is utter  nonsense!"
                +"How dare you to speak to me like that?"
                +"Do you know to whom you are speaking to?!"
                +"Let me tell you something. I am not going to put that mask on! I don't care about your stupid rules."
                +"Why do you get to decide what I can or cannot wear?"
                +"You are getting in my personal space! I want to speak to your manager!"
                +"Do you want to lose your job? You will be fired for pressuring me to do something that I don't want!"
                +"You are asking me to do something against my will!"
                +"I think your attitude is offensive and I'm not gonna tolerate it!"
                +"Don't you have something else to do, like working, instead of waiting my time?"
                +"Look, I am very aware of the rules. The thing is that I can't accept orders from someone that arranges the shelfs in the supermarket."
                +"I am sure that  neither we are on the same diploma level, nor on the same experience, so it will be wiser to just get out of my way."
            }
        }
        furhat.listen()
    }

    onResponse<ExtremeNegative>{
        furhat.say {
            furhat.gesture(Gestures.ExpressAnger(duration = 10.0), async = true)
            +"I will call the cops!!!"
        }
        furhat.listen()
    }

    onResponse {
        random(
            {furhat.ask("Yeah, yeah, can't you just let me finish my shopping and go home.")},
            {furhat.ask("Listen what, I had a really tough day and I can't listen to your rubbish anymore.")},
            {furhat.ask("Listen, listen, listen. I'm talking now!")},
            {furhat.ask("Yeah, I know the rules, but I am here for only 2 minutes, no one will notice that I am not wearing a mask.")},
            {furhat.ask("Oh, come on! Just let me buy the cereal I came for and I will leave as this conversation never happened! ")},
            {furhat.ask("Instead of continuing waisting each other's time as we've been doing it for 5 minutes you can just let it go!")},
            {furhat.ask("You know what? I got a bit tired of listening to the same rubbish again and again!")},
            {furhat.ask("One question, do you really think that convincing me to put a mask on will stop spreading the virus?")}
        )
        furhat.listen()
    }
    var noinput = 0
    var nomatch = 0
    onResponse {
        nomatch++
        if (nomatch > 1)
            furhat.say("I dare you repeat yourself?! What would I care about the rules!")
        else
            furhat.say("I have rights! And you can not tell me what to do!")
        furhat.listen()
    }

    onNoResponse {
        noinput++
        if (noinput > 1)
            furhat.say("I just refused to partake in this conspiracy. COVID is a hoax!")
        else
            furhat.say("What?!")
        furhat.listen()
    }

    onResponseFailed {
        furhat.say("I do not even want to have this conversation. I am leaving!")
        terminate()
    }

}




