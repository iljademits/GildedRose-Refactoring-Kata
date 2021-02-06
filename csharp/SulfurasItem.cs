using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp
{
    class SulfurasItem : DefaultItem
    {
        public const int SulfurasQuality = 80;
        public override void UpdateItemQuality()
        {
            this.Quality = SulfurasQuality;
        }
    }
}
